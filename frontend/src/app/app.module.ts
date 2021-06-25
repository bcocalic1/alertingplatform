import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { BodyComponent } from './components/body/body.component';
import { MenuComponent } from './components/menu/menu.component';
import { AlertCardComponent } from './components/alert-card/alert-card.component';
import { NewRuleButtonComponent } from './components/new-rule-button/new-rule-button.component';
import { RuleCardComponent } from './components/rule-card/rule-card.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    BodyComponent,
    MenuComponent,
    AlertCardComponent,
    NewRuleButtonComponent,
    RuleCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [BodyComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
