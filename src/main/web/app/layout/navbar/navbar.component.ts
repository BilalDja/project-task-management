import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

import {Account} from "../../core/auth/account.model";
import {AccountService} from "../../core/auth/account.service";
import App from "../../config/app.constants";
import {LoginService} from "../../auth/login/login.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  title = App.name;
  collapsed = false;
  account: Account | null = null;


  constructor(
    private router: Router,
    private loginService: LoginService,
    private accountService: AccountService) {
  }

  ngOnInit(): void {
    this.accountService.getAuthenticationState().subscribe(account => {
      this.account = account;
    });
  }

  logout(): void {
    this.loginService.logout();
    this.router.navigate(['']);
  }

}
