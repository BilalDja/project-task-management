import {Component, Input, OnInit} from '@angular/core';
import {ITask} from "../../task/task.model";

@Component({
  selector: 'app-task-important-item',
  templateUrl: './task-important-item.component.html',
  styleUrls: ['./task-important-item.component.scss']
})
export class TaskImportantItemComponent implements OnInit {

  @Input()
  task: ITask = {};

  constructor() { }

  ngOnInit(): void {
  }

}
