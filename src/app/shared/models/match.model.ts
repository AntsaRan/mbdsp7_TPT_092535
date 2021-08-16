import { Equipe } from "./equipe.model"

export class Match {
    id: string
    equipe1: Equipe
    equipe2:Equipe
    date: Date
    lieu:string
    etat: string
    scoreEquipe1:Number
    scoreEquipe2: Number
    etatlibel:string
}