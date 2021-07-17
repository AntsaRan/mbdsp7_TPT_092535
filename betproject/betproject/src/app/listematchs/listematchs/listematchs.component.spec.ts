import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListematchsComponent } from './listematchs.component';

describe('ListematchsComponent', () => {
  let component: ListematchsComponent;
  let fixture: ComponentFixture<ListematchsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListematchsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListematchsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
