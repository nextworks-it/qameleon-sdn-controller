import {Component, OnDestroy} from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { takeWhile } from 'rxjs/operators' ;
import { SolarData } from '../../@core/data/solar';

interface CardSettings {
  title: string;
  iconClass: string;
  type: string;
  link:string;
}

@Component({
  selector: 'ngx-dashboard',
  styleUrls: ['./dashboard.component.scss'],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnDestroy {

  private alive = true;

  solarValue: number;
  networkTopologyCard: CardSettings = {
    title: 'Network Topology',
    iconClass: 'nb-lightbulb',
    type: 'primary',
    link:'pages/networks/network-list'
  };
  lightPathsCard: CardSettings = {
    title: 'LightPaths',
    iconClass: 'nb-arrow-retweet',
    type: 'success',
    link:'pages/lightpaths/light-paths-list'
  };
  statusCards: string;

  commonStatusCardsSet: CardSettings[] = [
    this.networkTopologyCard,
    this.lightPathsCard,
  ];

  statusCardsByThemes: {
    default: CardSettings[];
    cosmic: CardSettings[];
    corporate: CardSettings[];
    dark: CardSettings[];
  } = {
    default: this.commonStatusCardsSet,
    cosmic: this.commonStatusCardsSet,
    corporate: [
      {
        ...this.networkTopologyCard,
        type: 'warning',
      },
      {
        ...this.lightPathsCard,
        type: 'primary',
      },
    ],
    dark: this.commonStatusCardsSet,
  };

  constructor(private themeService: NbThemeService,
              private solarService: SolarData) {
    this.themeService.getJsTheme()
      .pipe(takeWhile(() => this.alive))
      .subscribe(theme => {
        this.statusCards = this.statusCardsByThemes[theme.name];
    });

    this.solarService.getSolarData()
      .pipe(takeWhile(() => this.alive))
      .subscribe((data) => {
        this.solarValue = data;
      });
  }

  ngOnDestroy() {
    this.alive = false;
  }
}
