import {Component} from "@angular/core";
import {Hero} from "../hero";

@Component({
    moduleId: module.id,
    selector: 'hero-form',
    templateUrl: '../template/hero-form.component.html',
    styleUrls: ['../../node_modules/bootstrap/dist/css/bootstrap.min.css'],
    styles: [`
    .ng-valid[required], .ng-valid.required  {
      border-left: 5px solid #42A948; /* green */
    }
    .ng-invalid:not(form)  {
      border-left: 5px solid #a94442; /* red */
    }
    `]
})
export class HeroFormComponent{
    powers = ['Really Smart', 'Super Flexible', 'Super Hot', 'Weather Changer'];
    model = new Hero(18, 'Dr IQ', this.powers[0], 'Chuck Overstreet');

    submitted = false;
    active = true;

    newHero() {
        this.model = new Hero(42, '', '');
        this.active = false;
        setTimeout(() => this.active = true, 0);
    }

    onSubmit() { this.submitted = true; }

    // TODO: Remove this when we're done
    // get diagnostic() { return JSON.stringify(this.model); }
}