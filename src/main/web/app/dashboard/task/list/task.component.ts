import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AbstractControl, FormBuilder, Validators} from "@angular/forms";
import {Modal} from "bootstrap";
import {TaskService} from "../service/task.service";
import {DataTablesResponse} from "../../../shared/data-table/data-tables-response.model";
import {ITask} from "../task.model";
import {ADTSettings} from "angular-datatables/src/models/settings";
import DataTablesJsonFixer from "../../../shared/data-table/data-tables-json-fixer";
import {Status} from "../../../config/status.constants";
import {UserService} from "../../user/service/user.service";
import {IUserShort} from "../../user/user.model";
import {DataTableDirective} from "angular-datatables";
import {Priority} from "../../../config/priority.constants";
import {Account} from "../../../core/auth/account.model";
import {AccountService} from "../../../core/auth/account.service";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent implements OnInit {

  dataTableOptions: ADTSettings = {};
  modal: Modal | undefined;
  tasks: ITask[] = [];
  status = Status;
  priority = Priority;

  users: IUserShort[] = [];

  form = this.fb.group({
    taskId: [],
    title: [null, [Validators.required]],
    description: [null, [Validators.required]],
    status: ['REQUESTED', [Validators.required]],
    priority: ['LOW', [Validators.required]],
    userId: [null, [Validators.required]]
  });
  formErrors = {
    title: []
  };

  isCreated = true;
  isToggled = false;
  isSubmitted = false;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private taskService: TaskService,
    private userService: UserService,
    private accountService: AccountService) {
  }

  @ViewChild(DataTableDirective) datatableElement: DataTableDirective | undefined;

  account: Account | null = null;

  ngOnInit(): void {

    this.accountService.getAuthenticationState().subscribe(account => {
      this.account = account;
    });

    this.userService.all().subscribe(users => {
      this.users = users.body ? users.body : [];
    });

    const that = this;
    this.modal = new Modal('#taskModal', {
      backdrop: false,
      keyboard: false
    });
    this.dataTableOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      lengthMenu: [[5, 10, -1], [5, 10, "All"]],
      serverSide: true,
      processing: true,
      ajax: (dataTablesParameters: any, callback) => {
        that.http
          .get<DataTablesResponse>(
            'http://localhost:8080/api/tasksDataTable',
            {params: DataTablesJsonFixer(dataTablesParameters)}
          ).subscribe(resp => {
          that.tasks = resp.data;
          callback({
            recordsTotal: resp.recordsTotal,
            recordsFiltered: resp.recordsFiltered,
            data: []
          });
        });
      },
      columns: [
        {name: '#', data: 'taskId', searchable: false, orderable: true},
        {name: 'Title', data: 'title', searchable: true, orderable: true},
        {name: 'Status', data: 'status', searchable: true, orderable: true},
        {name: 'Priority', data: 'priority', searchable: true, orderable: true},
        {name: 'User', data: 'user.username', searchable: true, orderable: true},
        {name: 'Actions', data: null, searchable: false, orderable: false},
      ]
    };
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  toggleModal(task?: ITask) {
    this.isToggled = !this.isToggled;

    if (this.modal)
      this.modal.toggle();

    if (task && task.taskId && task.taskId > 0) {
      this.isCreated = false;
      this.form.setValue({
        taskId: task.taskId,
        title: task.title,
        description: task.description,
        status: task.status,
        priority: task.priority,
        userId: task.userId
      });
    }

    if (!this.isToggled) {
      this.isSubmitted = false;
      this.isCreated = true;
      this.form.reset();
    }
  }

  onSubmit(): void {
    this.formErrors = {
      title: []
    };
    this.isSubmitted = true;

    if (this.form.invalid) {
      return;
    }

    let observable = this.isCreated
      ? this.taskService.create({
        title: this.form.get('title')!.value,
        description: this.form.get('description')!.value,
        status: this.form.get('status')!.value,
        priority: this.form.get('priority')!.value,
        userId: this.form.get('userId')!.value,
      })
      : this.taskService.update({
        taskId: this.form.get('taskId')!.value,
        title: this.form.get('title')!.value,
        description: this.form.get('description')!.value,
        status: this.form.get('status')!.value,
        priority: this.form.get('priority')!.value,
        userId: this.form.get('userId')!.value,
      });

    observable.subscribe(
      () => {
        this.toggleModal();
        this.refreshDataTable();
      },
      (res) => {
        this.formErrors = res.error;
      }
    );
  }

  onDelete(id: number | undefined): void {
    if (id) {
      this.taskService.delete(id).subscribe(() => {
        this.refreshDataTable();
      });
    }
  }

  refreshDataTable() {
    if (this.datatableElement)
      this.datatableElement.dtInstance.then((dtInstance: DataTables.Api) => {
        dtInstance.ajax.reload()
      });
  }

}
