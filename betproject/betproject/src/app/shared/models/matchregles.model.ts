import { Equipe } from "./equipe.model"
import { Match } from "./match.model"
import { ReglesCotes } from "./reglescotes.model"

export class MatchRegles {
    id: string
    idmatch: Match
    regles:ReglesCotes[]=[]
}