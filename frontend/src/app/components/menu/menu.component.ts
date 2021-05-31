import { Component, OnInit } from '@angular/core';
import { BodyComponent } from '../body/body.component';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  alerts: boolean = true;
  rules: boolean = false;

  constructor(private body: BodyComponent) { }

  ngOnInit(): void {
  }

  alertsClicked(){
    this.rules = false;
    this.alerts = true;
    this.body.alerts = true;
  }

  rulesClicked(){
    this.rules = true;
    this.alerts = false;
    this.body.alerts = false;
  }

}
