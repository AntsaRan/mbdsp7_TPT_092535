import { Injectable } from '@angular/core';
import { Match } from '../models/match.model';
import { Paris } from '../models/paris.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private _items: { paris: Match }[] = [];

  constructor() {
    this._items = JSON.parse(localStorage.getItem('items') ||'[]'); // get the data at lunch 
  }

  remove(pari) {
    const index = this._items.indexOf(pari);
    this._items.splice(index,1);
    this.syncItems();
  }

  format(){
    localStorage.clear();
  }
  add(pari) { 
     this._items.push(pari);
     this.syncItems();
  }

  get length() : number{
    return this._items.length
  }

  get items(){
    return this._items.slice(0)
  }

  syncItems(){
    localStorage.setItem('items',JSON.stringify(this._items)); // sync the data

  }
}
