INSERT INTO `t_server` (id, discord_id, save_deleted) values
  (1, '00000000000000', '0'),
  (2, '00000000000001', '1');

INSERT INTO t_default_role(id, server_id, discord_id) VALUES
  (1, 1, '0000000000000'),
  (2, 2, '0000000000001');

INSERT INTO t_prefix(id, server_id, prefix) VALUES
  (1, 1, '#'),
  (2, 2, '_');

INSERT INTO t_subscription(id, server_id, channel_id, command, timer) VALUES
  (1, 1, '0000000000001', 'this is the first command', 6),
  (2, 1, '0000000000001', 'this is the second command', 3),
  (3, 2, '0000000000002', 'this is the third command', 6);
