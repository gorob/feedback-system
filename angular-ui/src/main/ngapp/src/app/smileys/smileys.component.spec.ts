import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SmileysComponent } from './smileys.component';

describe('SmileysComponent', () => {
  let component: SmileysComponent;
  let fixture: ComponentFixture<SmileysComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SmileysComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SmileysComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
