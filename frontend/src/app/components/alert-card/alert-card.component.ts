import { Component, OnInit } from '@angular/core';
import { AlertServiceService } from 'src/app/service/alert-service.service';

export class Alert {
  constructor(
    public name: String,
    public service: string,
    public severity: string,
    public description: string,
  ){}
}

@Component({
  selector: 'app-alert-card',
  templateUrl: './alert-card.component.html',
  styleUrls: ['./alert-card.component.css']
})
export class AlertCardComponent implements OnInit {

  alerts !: Alert[];

  constructor(private alertService: AlertServiceService) { }

  ngOnInit(): void {
    this.alertService.retrieveAllAlerts().subscribe(
      data => {
        console.log(data)
        this.alerts = data;
        console.log(this.alerts);
      }
    );
  }

}
