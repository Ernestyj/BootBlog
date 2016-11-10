import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Hero } from './hero';

@Injectable()
export class HeroService {

    private heroesUrl = 'http://localhost:8080/app/heroes';  // URL to web api

    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http:Http) {
    }

    getHeroes():Promise<Hero[]> {
        return this.http.get(this.heroesUrl)
            .toPromise()
            .then(response => {
                console.log(response.text());
                let heroes: Hero[] = [];
                let objs = response.json();
                for (let i = 0; i < objs.length; i++) {
                    let obj = objs[i];
                    heroes.push(new Hero(obj.id, obj.name));
                }
                return heroes;
            })
            .catch(this.handleError);
    }

    private handleError(error:any):Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    getHeroesSlowly():Promise<Hero[]> {
        return new Promise<Hero[]>(resolve =>
            setTimeout(resolve, 2000)) // delay 2 seconds
            .then(() => this.getHeroes());
    }

    getHero(id:number):Promise<Hero> {
        return this.getHeroes()
            .then(heroes => heroes.find(hero => hero.id === id));
    }

    update(hero: Hero): Promise<Hero> {
        const url = `${this.heroesUrl}/${hero.id}`;
        return this.http.put(url, JSON.stringify(hero), {headers: this.headers})
            .toPromise()
            .then(() => hero)
            .catch(this.handleError);
    }

    create(id: string, name: string): Promise<Hero> {
        return this.http
            .post(this.heroesUrl, JSON.stringify({id: id ,name: name}), {headers: this.headers})
            .toPromise()
            .then(res => {
                console.log(res.text());
                let obj = res.json();
                return new Hero(obj.id, obj.name);
            })
            .catch(this.handleError);
    }

    delete(id: number): Promise<void> {
        const url = `${this.heroesUrl}/${id}`;
        return this.http.delete(url, {headers: this.headers})
            .toPromise()
            .then(() => null)
            .catch(this.handleError);
    }
}


/*
 Copyright 2016 Google Inc. All Rights Reserved.
 Use of this source code is governed by an MIT-style license that
 can be found in the LICENSE file at http://angular.io/license
 */