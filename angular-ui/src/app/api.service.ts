import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Event } from './model/event.model';

@Injectable({
  providedIn: 'root'
})

export class APIService implements OnInit {

  restItems: Event;

  restItemsUrl = 'http://localhost:8080/v1/feedback';

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.getRestItems();
    console.log(this.restItems);
    return this.restItems;
  }

  // Read all REST Items
  getRestItems(): void {

    this.restItemsServiceGetRestItems()
      .subscribe(
        restItems => console.log(restItems)
      );
  }

  // Rest Items Service: Read all REST Items
  restItemsServiceGetRestItems() {

    return this.http
      .get<Event>(this.restItemsUrl + '/events')
      .pipe(map(data => data));
  }

}
