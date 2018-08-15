package com.lzw.myshop.portal.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
public interface AccountEntityRepository extends JpaRepository<AccountEntity, String> {

	AccountEntity findByAccountId(String accountId);
}
