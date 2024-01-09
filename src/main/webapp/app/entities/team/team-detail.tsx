import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './team.reducer';

export const TeamDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const teamEntity = useAppSelector(state => state.team.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="teamDetailsHeading">
          <Translate contentKey="dispatchApplicationApp.team.detail.title">Team</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{teamEntity.id}</dd>
          <dt>
            <span id="teamNo">
              <Translate contentKey="dispatchApplicationApp.team.teamNo">Team No</Translate>
            </span>
          </dt>
          <dd>{teamEntity.teamNo}</dd>
          <dt>
            <span id="supervisor">
              <Translate contentKey="dispatchApplicationApp.team.supervisor">Supervisor</Translate>
            </span>
          </dt>
          <dd>{teamEntity.supervisor}</dd>
          <dt>
            <span id="supervisorPhoneNo">
              <Translate contentKey="dispatchApplicationApp.team.supervisorPhoneNo">Supervisor Phone No</Translate>
            </span>
          </dt>
          <dd>{teamEntity.supervisorPhoneNo}</dd>
          <dt>
            <span id="teamLeader">
              <Translate contentKey="dispatchApplicationApp.team.teamLeader">Team Leader</Translate>
            </span>
          </dt>
          <dd>{teamEntity.teamLeader}</dd>
          <dt>
            <span id="teamLeaderPhone">
              <Translate contentKey="dispatchApplicationApp.team.teamLeaderPhone">Team Leader Phone</Translate>
            </span>
          </dt>
          <dd>{teamEntity.teamLeaderPhone}</dd>
        </dl>
        <Button tag={Link} to="/team" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/team/${teamEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TeamDetail;
