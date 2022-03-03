package com.ruochen.service.impl;

import com.ruochen.mapper.BrandMapper;
import com.ruochen.pojo.Brand;
import com.ruochen.pojo.PageBean;
import com.ruochen.service.BrandService;
import com.ruochen.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;


public class BrandServiceImpl implements BrandService {
    // 1. 创建 SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {
        // 2. 获取 SqlSession 对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取 BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 4. 调用方法
        List<Brand> brands = brandMapper.selectAll();
        // 5. 释放资源
        sqlSession.close();

        return brands;
    }

    @Override
    public void add(Brand brand) {
        // 2. 获取 SqlSession 对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取 BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 4. 调用方法
        brandMapper.add(brand);
        // 5. 提交事务
        sqlSession.commit();
        // 6. 释放资源
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        // 2. 获取 SqlSession 对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取 BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 4. 调用方法
        brandMapper.deleteByIds(ids);
        // 5. 提交事务
        sqlSession.commit();
        // 6. 释放资源
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        // 2. 获取 SqlSession 对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取 BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 计算 开始索引 和 查询条目数
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        // 5. 查询当前页数据
        List<Brand> rows = brandMapper.selectByPage(begin, size);

        // 6. 查询总记录数
        int totalCount = brandMapper.selectTotalCount();

        // 7. 封装 pageBean 对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        // 8. 释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        // 2. 获取 SqlSession 对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取 BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 计算 开始索引 和 查询条目数
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        // 处理 brand 条件，模糊表达式
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");
        }
        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }

        // 5. 查询当前页数据
        List<Brand> rows = brandMapper.selectByPageAndCondition(begin, size, brand);

        // 6. 查询总记录数
        int totalCount = brandMapper.selectTotalCountByCondition(brand);

        // 7. 封装 pageBean 对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        // 8. 释放资源
        sqlSession.close();

        return pageBean;

    }

    @Override
    public void deleteById(int id) {
        // 2. 获取 SqlSession 对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取 BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 4. 调用方法
        brandMapper.deleteById(id);
        // 5. 提交事务
        sqlSession.commit();
        // 6. 释放资源
        sqlSession.close();
    }

    @Override
    public void update(Brand brand) {
        // 2. 获取 SqlSession 对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取 BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 4. 调用方法
        brandMapper.update(brand);
        // 5. 提交事务
        sqlSession.commit();
        // 6. 释放资源
        sqlSession.close();
    }
}
