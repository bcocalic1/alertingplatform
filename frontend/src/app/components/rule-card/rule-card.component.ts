import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { RuleServiceService } from 'src/app/service/rule-service.service';

export class Rule {
  constructor(
    public name: string,
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
  closeResult !: string;
  name !: string;
  service !: string;
  severity !: string;
  limit !: number;
  services: any[] = [
    { value: 1, name: 'CPU' },
    { value: 2, name: 'File' },
    { value: 3, name: 'Temperature' },
    { value: 4, name: 'User management' },
  ];
  severities: any[] = [
    { value: 1, name: 'Critical' },
    { value: 2, name: 'High' },
    { value: 3, name: 'Normal' },
    { value: 4, name: 'Low' },
  ];

  constructor(
    private ruleService: RuleServiceService,
    private modalService: NgbModal,
  ) { }

  ngOnInit(): void {
    this.refreshRules();
  }

  deleteRule(rule: Rule){
    var index = this.rules.indexOf(rule);
    this.rules.splice(index, 1);
    this.ruleService.deleteRule(rule.service).subscribe(
      response => {
        console.log(response);
      }
    );
  }

  refreshRules() {
    this.ruleService.retrieveAllRules().subscribe(
      response => {
        console.log(response);
        this.rules = response;
      }
    )
  }

  open(content: any, name: string, limit: number, service: string, severity: string) {
    this.name = name;
    this.limit = limit;
    this.service = service;
    this.severity = severity;
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }
  
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  selectService(id: number) {
    // this.selectedService = this.services[id-1]['name'];
    this.service = this.services[id-1]['name'];
  //  console.log(this.selectedService);
  }

  selectSeverity(id: number) {
    // this.selectedSeverity = this.severities[id-1]['name'];
    this.severity = this.severities[id-1]['name'];
  //  console.log(this.selectedSeverity)
  //  console.log(this.selectedService);
  }

}
