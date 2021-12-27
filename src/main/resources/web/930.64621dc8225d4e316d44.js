"use strict";(self.webpackChunktask_management=self.webpackChunktask_management||[]).push([[930],{8930:(E,f,n)=>{n.r(f),n.d(f,{UserModule:()=>S});var u=n(8583),c=n(4827),i=n(665),h=n(846),p=n(8488),d=n(7988),e=n(3018),g=n(1841);let b=(()=>{class r{constructor(t){this.http=t,this.resourceUrl="http://localhost:8080/api/users"}find(t){return this.http.get(`${this.resourceUrl}/${t}`,{observe:"response"})}create(t){return this.http.post(this.resourceUrl,t,{observe:"response"})}update(t){return this.http.put(`${this.resourceUrl}/${function(r){return r.userId}(t)}`,t,{observe:"response"})}delete(t){return this.http.delete(`${this.resourceUrl}/${t}`,{observe:"response"})}}return r.\u0275fac=function(t){return new(t||r)(e.LFG(g.eN))},r.\u0275prov=e.Yz7({token:r,factory:r.\u0275fac,providedIn:"root"}),r})();function v(r,s){if(1&r){const t=e.EpF();e.TgZ(0,"button",33),e.NdJ("click",function(){e.CHM(t);const a=e.oxw().$implicit;return e.oxw(2).onDelete(a.userId)}),e._UZ(1,"i",34),e._uU(2," Remove "),e.qZA()}}function _(r,s){if(1&r){const t=e.EpF();e.TgZ(0,"tr"),e.TgZ(1,"td"),e._uU(2),e.qZA(),e.TgZ(3,"td"),e._uU(4),e.qZA(),e.TgZ(5,"td"),e._uU(6),e.qZA(),e.TgZ(7,"td"),e._uU(8),e.qZA(),e.TgZ(9,"td"),e._uU(10),e.qZA(),e.TgZ(11,"td"),e._uU(12),e.qZA(),e.TgZ(13,"td"),e._uU(14),e.qZA(),e.TgZ(15,"td"),e.TgZ(16,"button",30),e.NdJ("click",function(){const l=e.CHM(t).$implicit;return e.oxw(2).toggleModal(l)}),e._UZ(17,"i",31),e._uU(18," Edit "),e.qZA(),e.YNc(19,v,3,0,"button",32),e.qZA(),e.qZA()}if(2&r){const t=s.$implicit,o=e.oxw(2);e.xp6(2),e.Oqu(t.userId),e.xp6(2),e.Oqu(t.firstName),e.xp6(2),e.Oqu(t.lastName),e.xp6(2),e.Oqu(t.username),e.xp6(2),e.Oqu(t.email),e.xp6(2),e.Oqu(t.roleName==o.authorities.ADMIN?1===t.userId?"super":"admin":"user"),e.xp6(2),e.Oqu(t.active?"active":"not-active"),e.xp6(5),e.Q6J("ngIf",1!==t.userId)}}function T(r,s){if(1&r&&(e.TgZ(0,"tbody"),e.YNc(1,_,20,8,"tr",29),e.qZA()),2&r){const t=e.oxw();e.xp6(1),e.Q6J("ngForOf",t.users)}}function U(r,s){1&r&&(e.TgZ(0,"tbody"),e.TgZ(1,"tr"),e.TgZ(2,"td",35),e._uU(3,"No data!"),e.qZA(),e.qZA(),e.qZA())}function q(r,s){if(1&r&&(e.TgZ(0,"div",36),e._uU(1),e.qZA()),2&r){const t=e.oxw();e.xp6(1),e.hij(" ",t.formErrors.firstName.length>0?t.formErrors.firstName[0]:"field is required"," ")}}function N(r,s){if(1&r&&(e.TgZ(0,"div",36),e._uU(1),e.qZA()),2&r){const t=e.oxw();e.xp6(1),e.hij(" ",t.formErrors.lastName.length>0?t.formErrors.lastName[0]:"field is required"," ")}}function A(r,s){if(1&r&&(e.TgZ(0,"div",36),e._uU(1),e.qZA()),2&r){const t=e.oxw();e.xp6(1),e.hij(" ",t.formErrors.username.length>0?t.formErrors.username[0]:"field is required"," ")}}function C(r,s){if(1&r&&(e.TgZ(0,"div",36),e._uU(1),e.qZA()),2&r){const t=e.oxw();e.xp6(1),e.hij(" ",t.formErrors.email.length>0?t.formErrors.email[0]:"field is required"," ")}}const m=function(r){return{"is-invalid":r}},I=[{path:"",component:(()=>{class r{constructor(t,o,a){this.fb=t,this.http=o,this.userService=a,this.authorities=p.W,this.dataTableOptions={},this.users=[],this.form=this.fb.group({userId:[],firstName:[null,[i.kI.required]],lastName:[null,[i.kI.required]],email:[null,[i.kI.required]],username:[null,[i.kI.required]],active:[!0,[i.kI.required]],roleId:[2,[i.kI.required]]}),this.formErrors={firstName:[],lastName:[],email:[],username:[]},this.isCreated=!0,this.isToggled=!1,this.isSubmitted=!1}ngOnInit(){const t=this;this.modal=new h.u_("#userModal",{backdrop:!1,keyboard:!1}),this.dataTableOptions={pagingType:"full_numbers",pageLength:2,serverSide:!0,processing:!0,ajax:(o,a)=>{t.http.post("http://localhost:8080/api/usersList",o,{}).subscribe(l=>{t.users=l.data,a({recordsTotal:l.recordsTotal,recordsFiltered:l.recordsFiltered,data:[]})})},columns:[{data:"userId"},{data:"title"}]}}get f(){return this.form.controls}toggleModal(t){this.isToggled=!this.isToggled,this.modal&&this.modal.toggle(),t&&(this.isCreated=!1,this.form.setValue({userId:null==t?void 0:t.userId,firstName:null==t?void 0:t.firstName,lastName:null==t?void 0:t.lastName,email:null==t?void 0:t.email,username:null==t?void 0:t.username,active:null==t?void 0:t.active,roleId:null==t?void 0:t.roleId})),this.isToggled||(this.isSubmitted=!1,this.isCreated=!0,this.form.reset({roleId:2,active:!0}))}onSubmit(){this.formErrors={firstName:[],lastName:[],email:[],username:[]},this.isSubmitted=!0,this.form.invalid||(this.isCreated?this.userService.create({firstName:this.form.get("firstName").value,lastName:this.form.get("lastName").value,email:this.form.get("email").value,username:this.form.get("username").value,active:!0,roleId:this.form.get("roleId").value}):this.userService.update({userId:this.form.get("userId").value,firstName:this.form.get("firstName").value,lastName:this.form.get("lastName").value,email:this.form.get("email").value,username:this.form.get("username").value,active:this.form.get("active").value,roleId:this.form.get("roleId").value})).subscribe(()=>{this.toggleModal(),this.refreshDataTable()},o=>{})}onDelete(t){t&&this.userService.delete(t).subscribe(()=>{this.refreshDataTable()})}refreshDataTable(){this.datatableElement&&this.datatableElement.dtInstance.then(t=>{t.ajax.reload()})}}return r.\u0275fac=function(t){return new(t||r)(e.Y36(i.qu),e.Y36(g.eN),e.Y36(b))},r.\u0275cmp=e.Xpm({type:r,selectors:[["app-user"]],viewQuery:function(t,o){if(1&t&&e.Gf(d.G,5),2&t){let a;e.iGM(a=e.CRH())&&(o.datatableElement=a.first)}},decls:75,vars:23,consts:[[1,"mb-4","d-flex","align-items-center"],[2,"width","50px"],[1,"btn","btn-sm","btn-info","text-light","shadow","ms-auto",3,"click"],["datatable","","id","userDataTable",1,"table","table-light","shadow","w-100",3,"dtOptions"],["ref",""],[2,"width","150px"],[4,"ngIf"],["id","userModal","tabindex","-1","aria-labelledby","userModalLabel","aria-hidden","true",1,"modal","fade",2,"backdrop-filter","blur(1px) brightness(90%)","transition","none"],[1,"modal-dialog","modal-dialog-centered"],[1,"modal-content","border-0","shadow-lg"],[1,"modal-body"],[3,"formGroup","ngSubmit"],[1,"fw-bold","text-uppercase"],[1,"mb-4"],["for","userName",1,"form-label"],["formControlName","firstName","type","text","id","firstName","placeholder","Enter a username",1,"form-control","form-control-lg",3,"ngClass"],["class","text-danger",4,"ngIf"],["formControlName","lastName","type","text","id","lastName","placeholder","Enter a lastName",1,"form-control","form-control-lg",3,"ngClass"],["formControlName","username","type","text","id","userName","placeholder","Enter a username",1,"form-control","form-control-lg",3,"ngClass"],["formControlName","email","type","email","id","email","placeholder","Enter a email",1,"form-control","form-control-lg",3,"ngClass"],["formControlName","roleId","id","roleId",1,"form-control","form-control-lg",3,"ngClass"],["value","1"],["value","2"],[1,"form-check","form-switch"],["formControlName","active","type","checkbox","id","active",1,"form-check-input"],["for","active",1,"form-check-label"],[1,""],["type","button",1,"btn","btn-secondary","me-2","shadow",3,"click"],["type","submit",1,"btn","btn-primary","shadow"],[4,"ngFor","ngForOf"],[1,"btn","btn-sm","text-light","shadow","btn-dark","me-2",3,"click"],[1,"fas","fa-edit"],["class","btn btn-sm text-light shadow btn-danger",3,"click",4,"ngIf"],[1,"btn","btn-sm","text-light","shadow","btn-danger",3,"click"],[1,"fas","fa-trash"],["colspan","3",1,"no-data-available"],[1,"text-danger"]],template:function(t,o){1&t&&(e.TgZ(0,"div",0),e.TgZ(1,"div"),e.TgZ(2,"h1"),e._uU(3,"List User"),e.qZA(),e._UZ(4,"hr",1),e.qZA(),e.TgZ(5,"button",2),e.NdJ("click",function(){return o.toggleModal()}),e._uU(6,"Add"),e.qZA(),e.qZA(),e.TgZ(7,"table",3,4),e.TgZ(9,"thead"),e.TgZ(10,"tr"),e.TgZ(11,"th"),e._uU(12,"#"),e.qZA(),e.TgZ(13,"th"),e._uU(14,"FirstName"),e.qZA(),e.TgZ(15,"th"),e._uU(16,"LastName"),e.qZA(),e.TgZ(17,"th"),e._uU(18,"Username"),e.qZA(),e.TgZ(19,"th"),e._uU(20,"Email"),e.qZA(),e.TgZ(21,"th"),e._uU(22,"Role"),e.qZA(),e.TgZ(23,"th"),e._uU(24,"Status"),e.qZA(),e.TgZ(25,"th",5),e._uU(26,"Actions"),e.qZA(),e.qZA(),e.qZA(),e.YNc(27,T,2,1,"tbody",6),e.YNc(28,U,4,0,"tbody",6),e.qZA(),e.TgZ(29,"div",7),e.TgZ(30,"div",8),e.TgZ(31,"div",9),e.TgZ(32,"div",10),e.TgZ(33,"form",11),e.NdJ("ngSubmit",function(){return o.onSubmit()}),e.TgZ(34,"h6",12),e._uU(35,"add user"),e.qZA(),e._UZ(36,"hr"),e.TgZ(37,"div",13),e.TgZ(38,"label",14),e._uU(39,"First Name"),e.qZA(),e._UZ(40,"input",15),e.YNc(41,q,2,1,"div",16),e.qZA(),e.TgZ(42,"div",13),e.TgZ(43,"label",14),e._uU(44,"Last Name"),e.qZA(),e._UZ(45,"input",17),e.YNc(46,N,2,1,"div",16),e.qZA(),e.TgZ(47,"div",13),e.TgZ(48,"label",14),e._uU(49,"Username"),e.qZA(),e._UZ(50,"input",18),e.YNc(51,A,2,1,"div",16),e.qZA(),e.TgZ(52,"div",13),e.TgZ(53,"label",14),e._uU(54,"Email"),e.qZA(),e._UZ(55,"input",19),e.YNc(56,C,2,1,"div",16),e.qZA(),e.TgZ(57,"div",13),e.TgZ(58,"label",14),e._uU(59,"Role"),e.qZA(),e.TgZ(60,"select",20),e.TgZ(61,"option",21),e._uU(62,"admin"),e.qZA(),e.TgZ(63,"option",22),e._uU(64,"user"),e.qZA(),e.qZA(),e.qZA(),e.TgZ(65,"div",23),e._UZ(66,"input",24),e.TgZ(67,"label",25),e._uU(68,"active"),e.qZA(),e.qZA(),e._UZ(69,"hr"),e.TgZ(70,"div",26),e.TgZ(71,"button",27),e.NdJ("click",function(){return o.toggleModal()}),e._uU(72,"Close"),e.qZA(),e.TgZ(73,"button",28),e._uU(74,"Save"),e.qZA(),e.qZA(),e.qZA(),e.qZA(),e.qZA(),e.qZA(),e.qZA()),2&t&&(e.xp6(7),e.Q6J("dtOptions",o.dataTableOptions),e.xp6(20),e.Q6J("ngIf",0!=(null==o.users?null:o.users.length)),e.xp6(1),e.Q6J("ngIf",0==(null==o.users?null:o.users.length)),e.xp6(5),e.Q6J("formGroup",o.form),e.xp6(7),e.Q6J("ngClass",e.VKq(13,m,o.isSubmitted&&o.f.firstName.errors)),e.xp6(1),e.Q6J("ngIf",o.isSubmitted&&(o.f.firstName.errors||o.formErrors.username.length>0)),e.xp6(4),e.Q6J("ngClass",e.VKq(15,m,o.isSubmitted&&o.f.lastName.errors)),e.xp6(1),e.Q6J("ngIf",o.isSubmitted&&(o.f.lastName.errors||o.formErrors.lastName.length>0)),e.xp6(4),e.Q6J("ngClass",e.VKq(17,m,o.isSubmitted&&o.f.username.errors)),e.xp6(1),e.Q6J("ngIf",o.isSubmitted&&(o.f.username.errors||o.formErrors.username.length>0)),e.xp6(4),e.Q6J("ngClass",e.VKq(19,m,o.isSubmitted&&o.f.email.errors)),e.xp6(1),e.Q6J("ngIf",o.isSubmitted&&(o.f.email.errors||o.formErrors.email.length>0)),e.xp6(4),e.Q6J("ngClass",e.VKq(21,m,o.isSubmitted&&o.f.roleId.errors)))},directives:[d.G,u.O5,i._Y,i.JL,i.sg,i.Fj,i.JJ,i.u,u.mk,i.EJ,i.YN,i.Kr,i.Wl,u.sg],styles:[""]}),r})()}];let x=(()=>{class r{}return r.\u0275fac=function(t){return new(t||r)},r.\u0275mod=e.oAB({type:r}),r.\u0275inj=e.cJS({imports:[[c.Bz.forChild(I)],c.Bz]}),r})();var y=n(4079);let S=(()=>{class r{}return r.\u0275fac=function(t){return new(t||r)},r.\u0275mod=e.oAB({type:r}),r.\u0275inj=e.cJS({imports:[[u.ez,x,d.T,i.UX,y.m]]}),r})()}}]);