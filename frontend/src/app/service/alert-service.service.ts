import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Alert } from '../components/alert-card/alert-card.component';

@Injectable({
  providedIn: 'root'
})
export class AlertServiceService {

  constructor(private http: HttpClient) { }

  retrieveAllAlerts(){
    return this.http.get<Alert[]>(`http://localhost:8080/api/v1/alerts`);
  }
}
