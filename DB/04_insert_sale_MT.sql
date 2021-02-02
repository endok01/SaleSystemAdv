conn SALESAMPLE/SALESAMPLE

insert into MT_ITEM_GENRE values('00000001','コピー用紙');
insert into MT_ITEM_GENRE values('00000002','クリアファイル');
insert into MT_ITEM_GENRE values('00000003','付箋');


insert into MT_ITEM values('CP000001', 'コピー用紙A4 01',       '00000001', 'A4',          3000);
insert into MT_ITEM values('CP000002', 'コピー用紙A3 01',       '00000001', 'A3',          3100);
insert into MT_ITEM values('CP000003', 'コピー用紙B5 01',       '00000001', 'B5',          2900);
insert into MT_ITEM values('CF000001', 'クリアファイル 10枚P',  '00000002', '10枚パック',   100);
insert into MT_ITEM values('CF000002', 'クリアファイル 100枚P', '00000002', '100枚パック',  900);
insert into MT_ITEM values('SN000001', '付箋紙 大 10P',         '00000003', '大',           700);
insert into MT_ITEM values('SN000002', '付箋紙 中 10P',         '00000003', '中',           500);
insert into MT_ITEM values('SN000003', '付箋紙 小 10P',         '00000003', '小',           300);

insert into MT_CUSTOMER values('C0000001', '新橋商店');
insert into MT_CUSTOMER values('C0000002', '虎ノ門商事');
insert into MT_CUSTOMER values('C0000003', '霞が関産業');
insert into MT_CUSTOMER values('O0000001', 'その他');

commit;