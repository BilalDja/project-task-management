import {Component, Input, OnInit, ViewChild, ViewChildren} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AbstractControl, FormBuilder, Validators} from "@angular/forms";
import {Modal} from "bootstrap";
import {UserService} from "../service/user.service";
import {DataTablesResponse} from "../../../shared/data-table/data-tables-response.model";
import {IUser} from "../user.model";
import {ADTSettings} from "angular-datatables/src/models/settings";
import {Authority} from "../../../config/authority.constants";
import {DataTableDirective} from 'angular-datatables';
import DataTablesJsonFixer from "../../../shared/data-table/data-tables-json-fixer";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  authorities = Authority;
  dataTableOptions: ADTSettings = {};
  modal: Modal | undefined;
  users: IUser[] = [];

  form = this.fb.group({
    userId: [],
    firstName: [null, [Validators.required]],
    lastName: [null, [Validators.required]],
    email: [null, [Validators.required]],
    username: [null, [Validators.required]],
    active: [true, [Validators.required]],
    roleId: [2, [Validators.required]]
  });
  formErrors = {
    firstName: [],
    lastName: [],
    email: [],
    username: [],
  };

  isCreated = true;
  isToggled = false;
  isSubmitted = false;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private userService: UserService) {
  }

  @ViewChild(DataTableDirective) datatableElement: DataTableDirective | undefined;

  ngOnInit(): void {
    const that = this;
    this.modal = new Modal('#userModal', {
      backdrop: false,
      keyboard: false
    });
    // @ts-ignore
    this.dataTableOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      lengthMenu: [[5, 10, -1], [5, 10, "All"]],
      serverSide: true,
      processing: true,
      ajax: (dataTablesParameters: any, callback) => {
        that.http
          .get<DataTablesResponse>(
            'http://localhost:8080/api/usersDataTable',
            {params: DataTablesJsonFixer(dataTablesParameters)}
          ).subscribe(resp => {
          that.users = resp.data;
          callback({
            recordsTotal: resp.recordsTotal,
            recordsFiltered: resp.recordsFiltered,
            data: []
          });
        });
      },
      columns: [
        {name: '#', data: 'userId', searchable: false, orderable: true},
        {name: 'User', data: 'firstName', searchable: true, orderable: true},
        {name: 'Username', data: 'username', searchable: true, orderable: true},
        {name: 'Email', data: 'email', searchable: true, orderable: true},
        {name: 'Role', data: 'roleName', searchable: false, orderable: true},
        {name: 'Status', data: 'active', searchable: false, orderable: true},
        {name: 'Actions', data: null, searchable: false, orderable: false},
      ],

    };
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  toggleModal(user?: IUser) {
    this.isToggled = !this.isToggled;

    if (this.modal)
      this.modal.toggle();

    if (user) {
      this.isCreated = false;
      this.form.setValue({
        userId: user?.userId,
        firstName: user?.firstName,
        lastName: user?.lastName,
        email: user?.email,
        username: user?.username,
        active: user?.active,
        roleId: user?.roleId
      });
    }

    if (!this.isToggled) {
      this.isSubmitted = false;
      this.isCreated = true;
      this.form.reset({roleId: 2, active: true});
    }
  }

  onSubmit(): void {
    this.formErrors = {
      firstName: [],
      lastName: [],
      email: [],
      username: [],
    };
    this.isSubmitted = true;

    if (this.form.invalid) {
      return;
    }

    let observable = this.isCreated
      ? this.userService.create({
        firstName: this.form.get('firstName')!.value,
        lastName: this.form.get('lastName')!.value,
        email: this.form.get('email')!.value,
        username: this.form.get('username')!.value,
        active: true,
        roleId: this.form.get('roleId')!.value,
      })
      : this.userService.update({
        userId: this.form.get('userId')!.value,
        firstName: this.form.get('firstName')!.value,
        lastName: this.form.get('lastName')!.value,
        email: this.form.get('email')!.value,
        username: this.form.get('username')!.value,
        active: this.form.get('active')!.value,
        roleId: this.form.get('roleId')!.value,
      });

    observable.subscribe(
      () => {
        this.toggleModal();
        this.refreshDataTable();
      },
      (res) => {
        //this.formErrors = res.error;
      }
    );
  }

  onDelete(id: number | undefined): void {
    if (id) {
      this.userService.delete(id).subscribe(() => {
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
