import {Component, OnInit} from '@angular/core';
import {IOverview} from "./overview.model";
import {OverviewService} from "./overview.service";

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {

  date = new Date();

  overview: IOverview = {
    taskRequestedTotal: 0,
    taskInProgressTotal: 0,
    taskCompletedTotal: 0,
    usersTotal: 0,
    importantTasks:[],
    recentTasks:[],
    users:[]
  };

  constructor(
    private overviewService: OverviewService
  ) {
  }

  ngOnInit(): void {
    this.overviewService.all().subscribe(overview => {
      if( overview.body) {
        this.overview = overview.body;
      }
    });
  }

}
