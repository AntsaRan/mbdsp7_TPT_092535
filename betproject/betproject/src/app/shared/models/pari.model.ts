import { Equipe } from "./equipe.model"
import { Match } from "./match.model"
import { MatchRegles } from "./matchregles.model"
import { Parieur } from "./parieur.model"
import { ReglesCotes } from "./reglescotes.model"

export class Pari {
    id: string
    idparieur:string
    idmatch: string
    idTypeRegle:string
    mise:number
}