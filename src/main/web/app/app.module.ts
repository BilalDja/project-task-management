  import { NgModule } from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgxWebstorageModule} from 'ngx-webstorage';

import { AppRoutingModule } from './app-routing.module';

import {JwtInterceptor} from './core/interceptor/jwt.interceptor';
import {MainComponent} from "./layout/main/main.component";
import {FooterComponent} from "./layout/footer/footer.component";
import {NavbarComponent} from "./layout/navbar/navbar.component";
import {ErrorComponent} from "./layout/error/error.component";
import {AuthExpiredInterceptor} from "./core/interceptor/auth-expired.interceptor";

@NgModule({
  declarations: [
    MainComponent,
    FooterComponent,
    NavbarComponent,
    ErrorComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot({ prefix: 'app', separator: '-', caseSensitive: true }),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthExpiredInterceptor,
      multi: true,
    },
  ],
  bootstrap: [MainComponent]
})
export class AppModule { }
