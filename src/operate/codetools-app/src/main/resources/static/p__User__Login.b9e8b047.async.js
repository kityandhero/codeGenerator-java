(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[16],{"7PPT":function(e,t,a){e.exports={login:"antd-pro-pages-user-login-components-login-index-login",getCaptcha:"antd-pro-pages-user-login-components-login-index-getCaptcha",icon:"antd-pro-pages-user-login-components-login-index-icon",other:"antd-pro-pages-user-login-components-login-index-other",register:"antd-pro-pages-user-login-components-login-index-register",prefixIcon:"antd-pro-pages-user-login-components-login-index-prefixIcon",submit:"antd-pro-pages-user-login-components-login-index-submit"}},CHL8:function(e,t,a){"use strict";var n=a("g09b");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0,a("+L6B");var r=n(a("2/Rp")),l=n(a("jehZ")),u=n(a("Y/ft"));a("y8nQ");var o=n(a("Vl3Y")),c=n(a("q1tI")),d=n(a("TSYQ")),i=n(a("7PPT")),s=o.default.Item,f=function(e){var t=e.className,a=(0,u.default)(e,["className"]),n=(0,d.default)(i.default.submit,t);return c.default.createElement(s,null,c.default.createElement(r.default,(0,l.default)({size:"large",className:n,type:"primary",htmlType:"submit"},a)))},p=f;t.default=p},K4Vm:function(e,t,a){"use strict";var n=a("tAuX"),r=a("g09b");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0,a("y8nQ");var l=r(a("Vl3Y"));a("Znn+");var u=r(a("ZTPi")),o=r(a("gWZ8")),c=r(a("qIgq")),d=n(a("q1tI")),i=r(a("yUgw")),s=r(a("TSYQ")),f=r(a("YM0q")),p=r(a("uOkS")),m=r(a("CHL8")),g=r(a("wqp7")),v=r(a("7PPT")),h=function(e){var t=e.className,a=(0,d.useState)([]),n=(0,c.default)(a,2),r=n[0],p=n[1],m=(0,d.useState)(),g=(0,c.default)(m,2),h=g[0],b=g[1],E=(0,i.default)("",{value:e.activeKey,onChange:e.onTabChange}),y=(0,c.default)(E,2),C=y[0],w=y[1],P=[],T=[];return d.default.Children.forEach(e.children,function(e){e&&("LoginTab"===e.type.typeName?P.push(e):T.push(e))}),d.default.createElement(f.default.Provider,{value:{tabUtil:{addTab:function(e){p([].concat((0,o.default)(r),[e]))},removeTab:function(e){p(r.filter(function(t){return t!==e}))}},updateActive:function(e){h[C]?h[C].push(e):h[C]=[e],b(h)}}},d.default.createElement("div",{className:(0,s.default)(t,v.default.login)},d.default.createElement(l.default,{form:e.from,onFinish:function(t){e.onSubmit&&e.onSubmit(t)}},r.length?d.default.createElement(d.default.Fragment,null,d.default.createElement(u.default,{animated:!1,className:v.default.tabs,activeKey:C,onChange:function(e){w(e)}},P),T):e.children)))};h.Tab=g.default,h.Submit=m.default,h.UserName=p.default.UserName,h.Password=p.default.Password,h.Mobile=p.default.Mobile,h.Captcha=p.default.Captcha;var b=h;t.default=b},OalY:function(e,t,a){"use strict";var n=a("g09b");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r=a("RBnf"),l=n(a("q1tI")),u=n(a("7PPT")),o={UserName:{props:{size:"large",id:"userName",prefix:l.default.createElement(r.UserOutlined,{style:{color:"#1890ff"},className:u.default.prefixIcon}),placeholder:"admin"},rules:[{required:!0,message:"Please enter username!"}]},Password:{props:{size:"large",prefix:l.default.createElement(r.LockTwoTone,{className:u.default.prefixIcon}),type:"password",id:"password",placeholder:"888888"},rules:[{required:!0,message:"Please enter password!"}]},Mobile:{props:{size:"large",prefix:l.default.createElement(r.MobileTwoTone,{className:u.default.prefixIcon}),placeholder:"mobile number"},rules:[{required:!0,message:"Please enter mobile number!"},{pattern:/^1\d{10}$/,message:"Wrong mobile number format!"}]},Captcha:{props:{size:"large",prefix:l.default.createElement(r.MailTwoTone,{className:u.default.prefixIcon}),placeholder:"captcha"},rules:[{required:!0,message:"Please enter Captcha!"}]}};t.default=o},TVJM:function(e,t,a){e.exports={main:"antd-pro-pages-user-login-style-main",icon:"antd-pro-pages-user-login-style-icon",other:"antd-pro-pages-user-login-style-other",register:"antd-pro-pages-user-login-style-register"}},YM0q:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=a("q1tI"),r=(0,n.createContext)({}),l=r;t.default=l},anxO:function(e,t,a){"use strict";var n=a("g09b");Object.defineProperty(t,"__esModule",{value:!0}),t.fakeAccountLogin=o,t.getFakeCaptcha=d;var r=n(a("d6i3")),l=n(a("1l/V")),u=n(a("t3Un"));function o(e){return c.apply(this,arguments)}function c(){return c=(0,l.default)(r.default.mark(function e(t){return r.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,u.default)("/api/login/account",{method:"POST",data:t}));case 1:case"end":return e.stop()}},e)})),c.apply(this,arguments)}function d(e){return i.apply(this,arguments)}function i(){return i=(0,l.default)(r.default.mark(function e(t){return r.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,u.default)("/api/login/captcha?mobile=".concat(t)));case 1:case"end":return e.stop()}},e)})),i.apply(this,arguments)}},uOkS:function(e,t,a){"use strict";var n=a("tAuX"),r=a("g09b");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0,a("14J3");var l=r(a("BMrR"));a("+L6B");var u=r(a("2/Rp"));a("jCWc");var o=r(a("kPKH"));a("5NDa");var c=r(a("5rEg")),d=r(a("jehZ")),i=r(a("d6i3"));a("miYZ");var s=r(a("tsqr")),f=r(a("1l/V")),p=r(a("Y/ft")),m=r(a("qIgq"));a("y8nQ");var g=r(a("Vl3Y")),v=n(a("q1tI")),h=r(a("BGR+")),b=a("anxO"),E=r(a("OalY")),y=r(a("YM0q")),C=r(a("7PPT")),w=g.default.Item,P=function(e){var t=e.onChange,a=e.defaultValue,n=e.customProps,r=void 0===n?{}:n,l=e.rules,u={rules:l||r.rules};return t&&(u.onChange=t),a&&(u.initialValue=a),u},T=function(e){var t=(0,v.useState)(e.countDown||0),a=(0,m.default)(t,2),n=a[0],r=a[1],g=(0,v.useState)(!1),E=(0,m.default)(g,2),y=E[0],T=E[1],x=(e.onChange,e.customProps),q=(e.defaultValue,e.rules,e.name),N=(e.getCaptchaButtonText,e.getCaptchaSecondText,e.updateActive,e.type),k=(e.tabUtil,(0,p.default)(e,["onChange","customProps","defaultValue","rules","name","getCaptchaButtonText","getCaptchaSecondText","updateActive","type","tabUtil"])),I=(0,v.useCallback)(function(){var e=(0,f.default)(i.default.mark(function e(t){var a;return i.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,(0,b.getFakeCaptcha)(t);case 2:if(a=e.sent,!1!==a){e.next=5;break}return e.abrupt("return");case 5:s.default.success("\u83b7\u53d6\u9a8c\u8bc1\u7801\u6210\u529f\uff01\u9a8c\u8bc1\u7801\u4e3a\uff1a1234"),T(!0);case 7:case"end":return e.stop()}},e)}));return function(t){return e.apply(this,arguments)}}(),[]);if((0,v.useEffect)(function(){var t=0,a=e.countDown;return y&&(t=window.setInterval(function(){r(function(e){return e<=1?(T(!1),clearInterval(t),a||60):e-1})},1e3)),function(){return clearInterval(t)}},[y]),!q)return null;var M=P(e),O=k||{};if("Captcha"===N){var S=(0,h.default)(O,["onGetCaptcha","countDown"]);return v.default.createElement(w,{shouldUpdate:!0},function(e){var t=e.getFieldValue;return v.default.createElement(l.default,{gutter:8},v.default.createElement(o.default,{span:16},v.default.createElement(w,(0,d.default)({name:q},M),v.default.createElement(c.default,(0,d.default)({},x,S)))),v.default.createElement(o.default,{span:8},v.default.createElement(u.default,{disabled:y,className:C.default.getCaptcha,size:"large",onClick:function(){var e=t("mobile");I(e)}},y?"".concat(n," \u79d2"):"\u83b7\u53d6\u9a8c\u8bc1\u7801")))})}return v.default.createElement(w,(0,d.default)({name:q},M),v.default.createElement(c.default,(0,d.default)({},x,O)))},x={};Object.keys(E.default).forEach(function(e){var t=E.default[e];x[e]=function(a){return v.default.createElement(y.default.Consumer,null,function(n){return v.default.createElement(T,(0,d.default)({customProps:t.props,rules:t.rules},a,{type:e},n,{updateActive:n.updateActive}))})}});var q=x;t.default=q},wqp7:function(e,t,a){"use strict";var n=a("tAuX"),r=a("g09b");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=r(a("jehZ"));a("Znn+");var u=r(a("ZTPi")),o=n(a("q1tI")),c=r(a("YM0q")),d=u.default.TabPane,i=function(){var e=0;return function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";return e+=1,"".concat(t).concat(e)}}(),s=function(e){(0,o.useEffect)(function(){var t=i("login-tab-"),a=e.tabUtil;a&&a.addTab(t)},[]);var t=e.children;return o.default.createElement(d,e,e.active&&t)},f=function(e){return o.default.createElement(c.default.Consumer,null,function(t){return o.default.createElement(s,(0,l.default)({tabUtil:t.tabUtil},e))})};f.typeName="LoginTab";var p=f;t.default=p},zzaQ:function(e,t,a){"use strict";var n=a("tAuX"),r=a("g09b");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0,a("sRBo");var l=r(a("kaz8")),u=r(a("p0pE")),o=r(a("qIgq"));a("fOrg");var c=r(a("+KLJ")),d=a("RBnf"),i=n(a("q1tI")),s=a("ArA+"),f=a("MuoO"),p=r(a("K4Vm")),m=r(a("TVJM")),g=p.default.Tab,v=p.default.UserName,h=p.default.Password,b=p.default.Mobile,E=p.default.Captcha,y=p.default.Submit,C=function(e){var t=e.content;return i.default.createElement(c.default,{style:{marginBottom:24},message:t,type:"error",showIcon:!0})},w=function(e){var t=e.userLogin,a=void 0===t?{}:t,n=e.submitting,r=a.status,c=a.type,f=(0,i.useState)(!0),w=(0,o.default)(f,2),P=w[0],T=w[1],x=(0,i.useState)("account"),q=(0,o.default)(x,2),N=q[0],k=q[1],I=function(t){var a=e.dispatch;a({type:"login/login",payload:(0,u.default)({},t,{type:N})})};return i.default.createElement("div",{className:m.default.main},i.default.createElement(p.default,{activeKey:N,onTabChange:k,onSubmit:I},i.default.createElement(g,{key:"account",tab:"\u8d26\u6237\u5bc6\u7801\u767b\u5f55"},"error"===r&&"account"===c&&!n&&i.default.createElement(C,{content:"\u8d26\u6237\u6216\u5bc6\u7801\u9519\u8bef\uff08admin/ant.design\uff09"}),i.default.createElement(v,{name:"userName",placeholder:"\u7528\u6237\u540d: admin or user",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u7528\u6237\u540d!"}]}),i.default.createElement(h,{name:"password",placeholder:"\u5bc6\u7801: ant.design",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u5bc6\u7801\uff01"}]})),i.default.createElement(g,{key:"mobile",tab:"\u624b\u673a\u53f7\u767b\u5f55"},"error"===r&&"mobile"===c&&!n&&i.default.createElement(C,{content:"\u9a8c\u8bc1\u7801\u9519\u8bef"}),i.default.createElement(b,{name:"mobile",placeholder:"\u624b\u673a\u53f7",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u624b\u673a\u53f7\uff01"},{pattern:/^1\d{10}$/,message:"\u624b\u673a\u53f7\u683c\u5f0f\u9519\u8bef\uff01"}]}),i.default.createElement(E,{name:"captcha",placeholder:"\u9a8c\u8bc1\u7801",countDown:120,getCaptchaButtonText:"",getCaptchaSecondText:"\u79d2",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801\uff01"}]})),i.default.createElement("div",null,i.default.createElement(l.default,{checked:P,onChange:function(e){return T(e.target.checked)}},"\u81ea\u52a8\u767b\u5f55"),i.default.createElement("a",{style:{float:"right"}},"\u5fd8\u8bb0\u5bc6\u7801")),i.default.createElement(y,{loading:n},"\u767b\u5f55"),i.default.createElement("div",{className:m.default.other},"\u5176\u4ed6\u767b\u5f55\u65b9\u5f0f",i.default.createElement(d.AlipayCircleOutlined,{className:m.default.icon}),i.default.createElement(d.TaobaoCircleOutlined,{className:m.default.icon}),i.default.createElement(d.WeiboCircleOutlined,{className:m.default.icon}),i.default.createElement(s.Link,{className:m.default.register,to:"/user/register"},"\u6ce8\u518c\u8d26\u6237"))))},P=(0,f.connect)(function(e){var t=e.login,a=e.loading;return{userLogin:t,submitting:a.effects["login/login"]}})(w);t.default=P}}]);