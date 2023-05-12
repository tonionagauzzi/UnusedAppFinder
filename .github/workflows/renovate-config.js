module.exports = {
    branchPrefix: 'renovate/',
    username: 'renovate-release',
    gitAuthor: 'Renovate Bot <bot@renovateapp.com>',
    onboarding: false,
    platform: 'github',
    includeForks: true,
    dryRun: 'full',
    repositories: ['tonionagauzzi/UnusedAppFinder'],
    labels: ['dependencies'],
    packageRules: [
      {
        description: 'lockFileMaintenance',
        matchUpdateTypes: [
            'bump',
            'digest',
            'lockFileMaintenance',
            'patch',
            'pin',
            'pinDigest',
            'rollback',
        ],
      },
    ],
  };
