import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  alerts: boolean = true;
  rules: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  alertsClicked(){
    this.rules = false;
    this.alerts = true;
  }

  rulesClicked(){
    this.rules = true;
    this.alerts = false;
  }

}
