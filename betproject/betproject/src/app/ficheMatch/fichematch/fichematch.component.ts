import { Component, OnInit } from '@angular/core';
import { Match } from 'src/app/shared/models/match.model';

@Component({
  selector: 'app-fichematch',
  templateUrl: './fichematch.component.html',
  styleUrls: ['./fichematch.component.css']
})
export class FichematchComponent implements OnInit {

  match: Match=null;
  constructor() { 
    this.getMatchById();
  }

  ngOnInit(): void {
  }
  getMatchById() {
    /*const id: number = + this.route.snapshot.params.id;
    console.log(id + " id getAssignementById");
    this.assignmentService.getAssignment(id)
      .subscribe(assignment => {
        this.assignmentTransmis = assignment;
        console.log(assignment.id_matiere['id']+ " ITO ILAY ID ANLAY MATIERE");
        this.getMatiereByID(assignment.id_matiere['id']);
        this.getProfbyIdMatiere(assignment.id_matiere['id']);
      })*/
  }
}
