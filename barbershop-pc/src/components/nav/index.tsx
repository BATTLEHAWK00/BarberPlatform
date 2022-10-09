import { Menu } from 'antd';
import { ItemType } from 'antd/lib/menu/hooks/useItems';
import { MenuClickEventHandler } from 'rc-menu/lib/interface';
import React from 'react';

const items: ItemType[] = [
  {
    key: 1,
    label: 'test',
  },
  {
    key: 2,
    label: 'test',
  },
  {
    type: 'divider',
  },
  {
    key: 3,
    label: 'test',
  },
  {
    key: 5,
    label: 'test',
  },
];

function Nav() {
  const handleClick: MenuClickEventHandler = (info) => {
    info.key;
  };

  return (
    <>
      <Menu items={items} mode="vertical" onClick={handleClick} />
    </>
  );
}
export default Nav;
