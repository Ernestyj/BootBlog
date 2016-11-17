export class Blog{
    constructor(
        public vid: number,//id
        public date?: string,//博客创建日期
        public title?: string,//博客标题，不可为空
        public article?: string,//博客内容的html文本
        public tags?: string,//标签，不同标签以,隔开
        public md?: string,//博客内容的markdown文本
        public monthDay?: string//形如"Oct 04",为了方便archives页面显示，并不对应数据库的任何一列
    ){}
}