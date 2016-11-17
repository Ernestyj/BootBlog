import {OnInit, Component} from "@angular/core";
import {MonitorService} from "../../service/monitor.service";

@Component({
    moduleId: module.id,
    selector: "admin-home",
    templateUrl: "admin-home.component.html",
    styleUrls: []
})
export class AdminHomeComponent implements OnInit {

    freeMemory: number;

    errorMessage: string;

    constructor(private monitorService: MonitorService){}

    ngOnInit(): void {
        this.getFreeMemery();
    }

    getFreeMemery(){
        this.monitorService.getFreeMemery().subscribe(
            freeMemory => {
                this.freeMemory = freeMemory;

                let script = document.createElement("script");
                script.type = "text/javascript";
                script.innerHTML = `
                Morris.Donut({
                    element: 'memory-donut',
                    data: [ {value: `+this.freeMemory+`, label: 'Residual memory'}, 
                        {value: `+(100-this.freeMemory)+`, label: 'Used memory'} ],
                    backgroundColor: '#ccc', labelColor: '#060', colors: [ '#0BA462', '#95D7BB' ],
                    formatter: function (x) { return x + "%"}
                });
                `;
                document.head.appendChild(script);
            },
            error => this.errorMessage = error
        );
    }

    get diagnostic(){
        return 'get freeMemory: '+this.freeMemory;
    }

}