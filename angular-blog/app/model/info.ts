
export class Info{
    constructor(
        public username: string,//用户名
        public password: string,//md5加密后的密码
        public email?: string,//邮箱,默认为#
        public github?: string,//github地址，默认为#
        public twitter?: string,//twitter地址，默认为#
        public md?: string,//简历的markdown文本，为了admin管理时能够回显
        public resume?: string,//简历的html文本
        public Avatar?: string//头像地址
    ){}

}