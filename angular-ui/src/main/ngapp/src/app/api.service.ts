import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { Event } from './model/event.model';
import { Question } from './model/question.model';

@Injectable({
  providedIn: 'root'
})

export class APIService implements OnInit {

  eventss: Event[];

  endPoint = 'http://localhost:8080/v1/feedback';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  private extractData(res: Response) {
  // tslint:disable-next-line:prefer-const
  let body = res;
  return body || { };
  }

  ngOnInit() {

  }


  public getEventsAsJson(): Promise<Event[]> {
    return this.http
               .get<Event>(this.endPoint + '/events').pipe(
               .map(response => {
                   const array = JSON.parse(response.json()) as any[]; // TODO hier muss was
                   const details = array.map(data => new Event(data));
                   return details;
               })).toPromise();
  }

  getEventsFromApi(): Observable<any> {
    return this.http.get(this.endPoint + '/events')
    .pipe(map(this.extractData));
  }

  getEvents(): Event[] {
    this.eventss = [];
    this.getEventsFromApi().subscribe((data?: []) => {
      this.parseData(data);
    });
    return this.eventss;
  }

  parseData(jsonData: string[]) {
    console.log(jsonData[0]);
    console.log('lenght ' + jsonData.length);
    for (let i = 0; jsonData.length; i++) {
      let questionsList: Question[];
      questionsList = [];
      // for (let j = 0; jsonData[i]['questions'].length - 1; j++) {
      //   const question = new Question(jsonData[i]['questions'][j]['id'], jsonData[i]['questions'][j]['questionName']);
      //   questionsList[j] = question;
      // }

        const question = new Question(1, 'Wie hat das Essen heute geschmeckt?');
        questionsList[0] = question;
      console.log('Fragen');
      console.log(jsonData[i]['name']);
      console.log(questionsList);


      const data = new Event(jsonData[i]['id'], jsonData[i]['name'], questionsList);
      this.eventss.push(data);
    }
  }

  private handleError<T> (operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // TODO: better job of transforming error for user consumption
    console.log(`${operation} failed: ${error.message}`);

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
}

}
