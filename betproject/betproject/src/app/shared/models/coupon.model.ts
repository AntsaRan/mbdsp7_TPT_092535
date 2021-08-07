import { Equipe } from "./equipe.model"
import { Match } from "./match.model"
import { MatchRegles } from "./matchregles.model"
import { Parieur } from "./parieur.model"
import { Regles } from "./regles.model"
import { ReglesCotes } from "./reglescotes.model"

export class Coupon {
    id: string
    match:Match
    regle:ReglesCotes
    idparieur:string
    idmatch: string
    idTypeRegle:string
    mise:number
    respbtn:string
}