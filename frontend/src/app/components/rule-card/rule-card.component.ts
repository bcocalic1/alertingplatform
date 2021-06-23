import { Component, OnInit } from '@angular/core';

export class Rule {
  constructor(
    public name: String,
    public service: string,
    public severity: string,
    public limit: number,
    public timePeriod: number,
    public timeUnit: string,
    public inARow: number,
  ) { }
}

@Component({
  selector: 'app-rule-card',
  templateUrl: './rule-card.component.html',
  styleUrls: ['./rule-card.component.css']
})
export class RuleCardComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
