import {Blog} from "./blog";

export class Archive{
    constructor(
        public year?: number,//年份
        public list?: Blog[]//此年份的博客列表
    ){}
}