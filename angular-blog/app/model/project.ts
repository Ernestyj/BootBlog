import {Timestamp} from "rxjs";

export class Project{
    constructor(
        public id: number,//id
        public name?: string,//项目名称
        public url?: string,//项目url地址，例如https://github.com/jcalaz/jcalaBlog
        public tech?: string,//项目所用技术
        public desp?: string,//项目描述
        public date?: string//项目创建时间
    ){}
}