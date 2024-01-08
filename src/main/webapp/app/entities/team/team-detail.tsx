import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
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
        <h2 data-cy="teamDetailsHeading">Team</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{teamEntity.id}</dd>
          <dt>
            <span id="teamNo">Team No</span>
          </dt>
          <dd>{teamEntity.teamNo}</dd>
          <dt>
            <span id="supervisor">Supervisor</span>
          </dt>
          <dd>{teamEntity.supervisor}</dd>
          <dt>
            <span id="supervisorPhoneNo">Supervisor Phone No</span>
          </dt>
          <dd>{teamEntity.supervisorPhoneNo}</dd>
          <dt>
            <span id="teamLeader">Team Leader</span>
          </dt>
          <dd>{teamEntity.teamLeader}</dd>
          <dt>
            <span id="teamLeaderPhone">Team Leader Phone</span>
          </dt>
          <dd>{teamEntity.teamLeaderPhone}</dd>
        </dl>
        <Button tag={Link} to="/team" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/team/${teamEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default TeamDetail;
