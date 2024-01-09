import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './service-provider.reducer';

export const ServiceProviderDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const serviceProviderEntity = useAppSelector(state => state.serviceProvider.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="serviceProviderDetailsHeading">
          <Translate contentKey="dispatchApplicationApp.serviceProvider.detail.title">ServiceProvider</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{serviceProviderEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="dispatchApplicationApp.serviceProvider.name">Name</Translate>
            </span>
          </dt>
          <dd>{serviceProviderEntity.name}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="dispatchApplicationApp.serviceProvider.code">Code</Translate>
            </span>
          </dt>
          <dd>{serviceProviderEntity.code}</dd>
          <dt>
            <span id="phone">
              <Translate contentKey="dispatchApplicationApp.serviceProvider.phone">Phone</Translate>
            </span>
          </dt>
          <dd>{serviceProviderEntity.phone}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="dispatchApplicationApp.serviceProvider.address">Address</Translate>
            </span>
          </dt>
          <dd>{serviceProviderEntity.address}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="dispatchApplicationApp.serviceProvider.status">Status</Translate>
            </span>
          </dt>
          <dd>{serviceProviderEntity.status}</dd>
        </dl>
        <Button tag={Link} to="/service-provider" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/service-provider/${serviceProviderEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ServiceProviderDetail;
