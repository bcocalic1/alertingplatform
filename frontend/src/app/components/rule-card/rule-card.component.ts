import { Component, OnInit } from '@angular/core';
import { RuleServiceService } from 'src/app/service/rule-service.service';

export class Rule {
  constructor(
    public name: String,
    public service: string,
    public severity: string,
    public limit: number,
    public timePeriod: number,
    public timeUnit: string,
    public inARow: number,
    public type: string,
  ) { }

  public getDescription() : string {
    return `More than ${this.limit} wrong measurements`
    // ${this.timePeriod} ${this.timeUnit}`;
  }
}

@Component({
  selector: 'app-rule-card',
  templateUrl: './rule-card.component.html',
  styleUrls: ['./rule-card.component.css']
})
export class RuleCardComponent implements OnInit {

  rules !: Rule[];

  constructor(
    private ruleService: RuleServiceService,
  ) { }

  ngOnInit(): void {
    this.refreshRules();
  }

  refreshRules() {
    this.ruleService.retrieveAllRules().subscribe(
      response => {
        console.log(response);
        this.rules = response;
      }
    )
  }

}
