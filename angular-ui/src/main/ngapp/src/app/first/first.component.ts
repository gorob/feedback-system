import { Component, OnInit } from '@angular/core';
import { APIService } from '../api.service';
import { Event } from '../model/event.model';
import { Question } from '../model/question.model';
import { NavigationComponent } from '../navigation/navigation.component';

@Component({
  selector: 'app-first',
  templateUrl: './first.component.html',
  styleUrls: ['./first.component.css']
})
export class FirstComponent implements OnInit {

  eventsArray: Event[] = [];
  questionsArray: Question[] = [];
  public events: Event;

  constructor(private apiService: APIService, private navigation: NavigationComponent) { }

  ngOnInit() {
    this.eventsArray = this.apiService.getEvents();
    this.questionsArray = this.navigation.getQuestionsToEvent();
  }
}
