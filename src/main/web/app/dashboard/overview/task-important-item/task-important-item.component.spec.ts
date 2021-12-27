import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskImportantItemComponent } from './task-important-item.component';

describe('TaskImportantItemComponent', () => {
  let component: TaskImportantItemComponent;
  let fixture: ComponentFixture<TaskImportantItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TaskImportantItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskImportantItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
