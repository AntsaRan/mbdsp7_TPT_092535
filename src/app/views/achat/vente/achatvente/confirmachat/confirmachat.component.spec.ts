import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmachatComponent } from './confirmachat.component';

describe('ConfirmachatComponent', () => {
  let component: ConfirmachatComponent;
  let fixture: ComponentFixture<ConfirmachatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfirmachatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmachatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
