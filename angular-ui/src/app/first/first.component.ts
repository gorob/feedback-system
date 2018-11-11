import { Component, OnInit } from '@angular/core';
import { APIService } from '../api.service';
import { Event } from '../model/event.model';

@Component({
  selector: 'app-first',
  templateUrl: './first.component.html',
  styleUrls: ['./first.component.css']
})
export class FirstComponent implements OnInit {

  // public events: Array<object> = [];

  public events: Event;

  constructor(private apiService: APIService) { }

  ngOnInit() {
    this.apiService.getRestItems();
  }




}
