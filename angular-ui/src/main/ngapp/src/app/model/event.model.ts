
import { Question } from './question.model';

export class Event {
  id: number;
  name: string;
  questions: Question[];

  constructor(id, name, questions) {
    this.id = id;
    this.name = name;
    this.questions = questions;
  }

  getId() {
    return this.id;
  }
}
