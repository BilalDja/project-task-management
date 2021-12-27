import { Component, OnInit } from '@angular/core';
import {Account} from "../../core/auth/account.model";
import {AccountService} from "../../core/auth/account.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  account: Account | null = null;

  constructor(private accountService: AccountService) { }

  ngOnInit(): void {
    this.accountService.getAuthenticationState().subscribe(account => {
      this.account = account;
    });
  }

}
