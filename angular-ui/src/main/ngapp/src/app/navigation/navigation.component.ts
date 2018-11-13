import { Component, OnInit } from '@angular/core';
import { Event } from '../model/event.model';
import { Question } from '../model/question.model';
import { APIService } from '../api.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  eventsArray: Event[] = [];
  questionsArray: Question[] = [];

  constructor(private apiService: APIService) { }

  ngOnInit() {
    this.eventsArray = this.apiService.getEvents(); // this.apiService.getEvents();
  }

  setQuestionsForId(id) {
    for (let i = 0; this.eventsArray.length - 1; i++) {
      if (this.eventsArray[i].getId === id) {
        this.questionsArray = this.eventsArray[i].questions;
      }
    }

  }

  getQuestionsToEvent(): Question[] {
    return this.questionsArray;
  }

}
