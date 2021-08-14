import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MappingUpdateComponent } from './mapping-update.component';

describe('MappingUpdateComponent', () => {
  let component: MappingUpdateComponent;
  let fixture: ComponentFixture<MappingUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MappingUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MappingUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
