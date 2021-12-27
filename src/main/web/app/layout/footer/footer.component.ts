import { Component, OnInit } from '@angular/core';
import App from "../../config/app.constants";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  title = App.name;

  constructor() { }

  ngOnInit(): void {
  }

}
