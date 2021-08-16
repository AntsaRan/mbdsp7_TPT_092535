import { Component, OnInit } from '@angular/core';
import { getStyle, hexToRgba } from '@coreui/coreui/dist/js/coreui-utilities';
import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips';
import { Router } from '@angular/router';
import { AuthService } from '../../shared/services/auth.service';
import { StatService } from '../../shared/services/stat.service';

@Component({
  templateUrl: 'dashboard.component.html'
})
export class DashboardComponent implements OnInit {

  constructor(private route: Router, private auth: AuthService, private stat: StatService) {

  }


  radioModel: string = 'Month';
  loading: boolean = true;
  // mainChart
  public today: Date = new Date();
  public Month = this.today.getMonth();
  public year = this.today.getFullYear();
  months = ["Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
    "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"];
  jours = ["Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"];
  selectedMonthName = this.months[this.Month];
  public mainChartElements;
  public mainChartData1: Array<number> = [];
  public mainChartData2: Array<number> = [];
  public mainChartData3: Array<number> = [];
  public mainChartLegend = false;
  public mainChartType = 'line';
  totalParis: number = 0;
  // Pie
  public pieChartLabels: string[] = [];
  public pieChartData: number[] = [];
  public pieChartType = 'pie';

  public mainChartData: Array<any>;
  /* tslint:disable:max-line-length */
  public datesdumois: Array<Date> = [];
  public nbparisdate: Array<any>;
  public mainChartLabels: Array<any> = [];
  /* tslint:enable:max-line-length */
  public mainChartOptions: any;
  public mainChartColours: Array<any> = [
    { // brandInfo
      backgroundColor: hexToRgba(getStyle('--info'), 10),
      borderColor: getStyle('--info'),
      pointHoverBackgroundColor: '#fff'
    },
    { // brandSuccess
      backgroundColor: 'transparent',
      borderColor: getStyle('--success'),
      pointHoverBackgroundColor: '#fff'
    },
    { // brandDanger
      backgroundColor: 'transparent',
      borderColor: getStyle('--danger'),
      pointHoverBackgroundColor: '#fff',
      borderWidth: 1,
      borderDash: [8, 5]
    }
  ];

  getDaysInMonthUTC(month, year) {
    var date = new Date(Date.UTC(year, month, 1));
    var days = [];
    while (date.getUTCMonth() === month) {
      days.push(new Date(date));
      date.setUTCDate(date.getUTCDate() + 1);
    }
    return days;
  }

  initializechartvalues(tab: number[]) {
    console.log(tab.length + " tab.i")
    for (let i = 0; i < tab.length; ++i) {
      tab[i] = 0;
    }
    for (let i = 0; i < tab.length; ++i) {
      console.log(tab[i] + " tab.i")
    }
  }
  setMainchartoption(num) {
    this.mainChartOptions = {
      tooltips: {
        enabled: false,
        custom: CustomTooltips,
        intersect: true,
        mode: 'index',
        position: 'nearest',
        callbacks: {
          labelColor: function (tooltipItem, chart) {
            return { backgroundColor: chart.data.datasets[tooltipItem.datasetIndex].borderColor };
          }
        }
      },
      responsive: true,
      maintainAspectRatio: false,
      scales: {
        xAxes: [{
          gridLines: {
            drawOnChartArea: false,
          },
          ticks: {
            callback: function (value: any) {
              return value.charAt(0);
            }
          }
        }],
        yAxes: [{
          ticks: {
            beginAtZero: true,
            maxTicksLimit: 5,
            stepSize: Math.ceil(num / 5),
            max: num
          }
        }]
      },
      elements: {
        line: { 
          borderWidth: 2
        },
        point: {
          radius: 0,
          hitRadius: 10,
          hoverRadius: 4,
          hoverBorderWidth: 3,
        }
      },
      legend: {
        display: false
      }
    };
  }
  ngOnInit(): void {
    if (localStorage.getItem('currentUser')) {
      this.datesdumois = this.getDaysInMonthUTC(this.Month, this.year);
      this.nbparisdate = new Array<number>(this.datesdumois.length);
      this.initializechartvalues(this.nbparisdate);
      this.stat.getParisMonth()
        .subscribe(data => {
          data.forEach(p => {
            let date = new Date(p.dateParis);
            let jour = date.getUTCDate();
            let val = this.nbparisdate[jour];
            this.nbparisdate[jour] = val + 1;
            let mise: number = p.mise;
            this.totalParis += mise;
          }
          )
          this.setMainchartoption(data.length);
          this.mainChartData = [
            {
              data: this.nbparisdate,
              label: 'Nombre'
            }
          ];
          this.datesdumois.forEach(d => {
            let date = d.toISOString().split('T')[0]
            this.mainChartLabels.push(this.jours[d.getDay()] + " " + date);
          })
        })
      this.stat.getAchats()
        .subscribe(m => {
          console.log(m + " m")
          this.stat.getVentes()
            .subscribe(v => {
              console.log(v + " v")
              this.pieChartLabels = ['Achats', 'Ventes'];
              this.pieChartData.push(m);
              this.pieChartData.push(v);
              this.loading = false;
            })
        })
    } else {
      this.route.navigate(['login']);
    }


  }

  public random(min: number, max: number) {
    return Math.floor(Math.random() * (max - min + 1) + min);
  }

}
