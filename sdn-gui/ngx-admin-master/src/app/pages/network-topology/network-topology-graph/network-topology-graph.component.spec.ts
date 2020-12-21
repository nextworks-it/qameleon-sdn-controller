import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NetworkTopologyGraphComponent } from './network-topology-graph.component';

describe('NetworkTopologyGraphComponent', () => {
  let component: NetworkTopologyGraphComponent;
  let fixture: ComponentFixture<NetworkTopologyGraphComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NetworkTopologyGraphComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NetworkTopologyGraphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
