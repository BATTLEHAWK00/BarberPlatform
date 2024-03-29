import { Menu } from 'antd';
import { ItemType } from 'antd/lib/menu/hooks/useItems';
import { MenuClickEventHandler } from 'rc-menu/lib/interface';
import React from 'react';
import { useNavigate } from 'react-router-dom';
import routes from '@/routes/pages';
import { RouteObjectWithInfo } from '@/routes';

const Nav: React.FC = () => {
  const navigate = useNavigate();

  const handleClick: MenuClickEventHandler = (info) => {
    info.key;
  };

  function parseRoute(
    routes: RouteObjectWithInfo[],
    prefixPaths: string[] = [],
    depth = 1
  ): ItemType[] | undefined {
    if (depth > 2) return undefined;
    return routes
      .filter((route) => !route.hiddenInMenu)
      .filter((route) => route.path !== undefined)
      .map(({ path, menuIcon, menuName, children }) => {
        const fullPath = [...prefixPaths, path].join('/');
        return {
          key: fullPath,
          label: menuName,
          icon: menuIcon,
          children: children && parseRoute(children, [...prefixPaths, path as string], depth + 1),
          onClick: (e) => navigate(e.key),
        };
      });
  }

  const menuItems: ItemType[] = parseRoute(routes) as ItemType[];

  return (
    <>
      <Menu items={menuItems} mode="inline" onClick={handleClick} />
    </>
  );
};
export default Nav;
