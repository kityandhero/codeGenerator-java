(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[1],{"0Fdr":function(e,t,a){"use strict";var r=a("u+rM");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var s=r(a("smUt")),u=r(a("NTxs")),n=a("dCQc"),d=a("34ay"),i=a("HZnN"),o={namespace:"register",state:{status:void 0},effects:{submit:u.default.mark(function e(t,a){var r,s,d,i;return u.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return r=t.payload,s=a.call,d=a.put,e.next=4,s(n.fakeRegister,r);case 4:return i=e.sent,e.next=7,d({type:"registerHandle",payload:i});case 7:case"end":return e.stop()}},e)})},reducers:{registerHandle:function(e,t){var a=t.payload;return(0,d.setAuthority)("user"),(0,i.reloadAuthorized)(),(0,s.default)({},e,{status:a.status})}}};t.default=o}}]);